MyStack SEGMENT STACK
	DW 256 DUP (?) ; Create the stack
MyStack ENDS

MyData SEGMENT
	msg DB "Enter a number: $"
	screenMem EQU 0B800h ; Screen memory address
	fileName DB "nums.txt",0 ; Name of the file
	inFileHandle DW ? ; Filled in later
	maxBytesToRead DB 6
	numBytesRead DB ?
	charsRead DW 7 DUP (?) ; Filled in later
	total DW 6 DUP (?) ; Contain the total
	intCode DW 6 DUP (?)
	intCodeEnd DW 0 ; Terminator
	outputBuffer DB 5 DUP (?), 13, 10
	outputEnd DB 0 ; Terminator
MyData ENDS

MyCode SEGMENT
	ASSUME CS:MyCode, DS:MyData
	
	MOV	AX,MyData
	MOV DS,AX ; Move MyData into DS
	
	MOV AX,screenMem
	MOV ES,AX ; Set ES as the screen memory location
	
	main PROC
		CALL displayPrompt
		CALL openFile
		CALL loopProc
		CALL closeFile
		CALL endProgram
	ENDP main
	
	loopProc PROC
		MOV CX,65001 ; Move the integer 65001 into CX for the top of loop (will lower by one at the beginning)
		startLoop:
			;MOV DI, OFFSET outputEnd ; Make DI point to the output buffer
			LEA DI, outputEnd
			DEC CX ; Lower CX by one
			MOV AX,CX ; Put AX as the number needing to be put next
			convertLoop: ; Convert into ASCII code
				DEC DI ; Lower the position of DI by one
				MOV BX,10 ; Store 10 in BX
				MOV DX,0 ; Ensure it is 0
				DIV BX ; Divide AX by BX
				ADD DL,'0' ; Make DL into the ASCII code
				MOV [DI],DL ; Move DL into the position in memory DI is
				CMP AX,0 ; Check if AX is zero
				JNE convertLoop ; If not, then repeat
			PUSH AX BX CX ; Push the values onto the stack because we need to modify them only temporaily
			MOV BX,inFileHandle ; Set BX to the file handler
			MOV CX,8 ; Write this many bytes to the file
			LEA DX, outputBuffer ; Set DX to the memory address of outputBuffer
			MOV AH, 40h ; Write to file
			INT 21h ; Write to file
			POP CX BX AX ; Restore the values
			; put it into the file buffer
			CMP CX,charsRead ; Compare CX to the number read
			JE done3 ; If equal, leave the loop
			JMP startLoop ; If not equal, repeat
		done3:	
			RET
	loopProc ENDP
	
	displayPrompt PROC
		LEA DX,msg
		MOV CX,1
		MOV AH,09h
		INT 21h ; Display the message
		CALL grabInput ; After displaying the message, grab the input that the user wants to use
		RET
	ENDP displayPrompt
	
	grabInput PROC
		LEA DX,maxBytesToRead
		MOV AH, 0Ah
		INT 21h ; Read from the keyboard
		CALL asciiConvert
		RET
	ENDP grabInput
	
	asciiConvert PROC
		MOV SI, 0 ; Set SI to 0 
		MOV AL,0 ; Total counter
		MOV BL,10 ; Multiply constant
		topConvertLoop:
			MOV CL, OFFSET charsRead + [SI]
			;LEA CX, charsRead
			INC SI
			CMP CX,0Dh ; Error
			JE done2
			MUL BL
			SUB CX,'0'
			ADD AL,CL
			JMP topConvertLoop
		done2:
			MOV total,AX
			RET
	ENDP asciiConvert
	
	openFile PROC
		;MOV DX, OFFSET fileName ; Move filename into DX
		LEA DX, fileName
		MOV AL,2 ; Set file into read/write (should probably just be write)
		MOV AH,3Dh
		INT 21h ; Try opening the file
		JC errorHandler ; If an error happens, go into error handling mode
		MOV inFileHandle,AX ; If there is no error, save handle for later use
		JMP done ; Goto done
		
		errorHandler:
			CMP AX,2 ; Check if the file does not exist
			JMP createFile ; If it does not, create the file
			JMP done ; If the above is not true, finish the proc
			
		createFile:
			MOV AH,3Ch
			INT 21h ; Create the file if it does not exist
			MOV inFileHandle,AX ; Save file handle for later
			JMP done ; After the process is done, go to done
		
		done:
			RET
	ENDP openFile
	
	closeFile PROC
		MOV BX,inFileHandle ; Move given file handle into BX to close
		MOV AH,3Eh
		INT 21h ; Attempt to close the actual file
		RET
	ENDP closeFile
	
	endProgram PROC
		CALL closeFile ; Called here as this is the end of the program. Closes the given file
		MOV AH,4Ch
		INT 21h ; Exit the program
	ENDP endProgram
MyCode ENDS
END main ; Start at main