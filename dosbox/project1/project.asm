MyStack SEGMENT STACK
	DW  256   DUP (?)
MyStack ENDS

MyData SEGMENT
	screenMem EQU 0B800h ; Declare screen memory literal
	startDILine EQU 25 ; The line that DI starts on
	currentLine DW 0 ; The line that loopb is on
MyData ENDS

MyCode SEGMENT
	Assume CS:MyCode, DS:MyData

	main PROC
		MOV   AX,MyData ; Move MyData into AX
		MOV   DS,AX ; Set DS as MyData
		
		MOV   AX,ScreenMem ; Transfer the screen memory literal into AX
		MOV   ES,AX ; Set ES to the screen memory literal
		
		MOV SI,0 ; Set the SI register to be 0 for screen swap
		MOV DI,80+(160*startDILine) ; Set the DI register to be the bottom of the screen for screen swap
		CALL flip
		
		MOV     AH, 4Ch                ; DOS interrupt to return control to DOS
		INT     21h
	main ENDP
	
	flip PROC ; Flip everything in the console
	PUSH SI DI ; Store the values to get back when checking color
	loopa:
		MOV AL,ES:[SI] ; Get the first character, and then store it in AX
		MOV BL,ES:[DI] ; Get the second character, and store it in BX
		MOV ES:[SI],BL ; Swap the part in memory with the first character with the second character
		MOV ES:[DI],AL ; Swap the part in memory with the second character with the first character
		CMP DI,SI ; Compare if they are the same
		JE loopba ; If equal, go out of the loop
		ADD SI,2 ; Add 2 to the SI register to get the next character
		SUB DI,2 ; Subtract 2 from the DI register to get the previous character
		JMP loopa ; loop the process until pushed out
	loopb: ; Update the registers to the previous line
		SUB SI,40 ; Reset SI to the first position on the row
		SUB SI,160 ; Move SI to the previous row
		ADD DI,40 ; Reset DI to the last position on the row
		SUB DI,160 ; Move DI to the previous row
		JMP fix ; Goto fix
	loopba: ; Temp jump point to set the registers correctly
		MOV SI,25*160+0 ; Set SI to the first position on the last row
		MOV DI,25*160+80 ; Set DI to the last position on the last row
		JMP fix ; Start the fix process
	fix: ; Fix the reversed lines
		MOV AL,ES:[SI] ; Get the first character, and then store it in AX
		MOV BL,ES:[DI] ; Get the second character, and store it in BX
		MOV ES:[SI],BL ; Swap the part in memory with the first character with the second character
		MOV ES:[DI],AL ; Swap the part in memory with the second character with the first character
		ADD SI,2 ; Add 2 to SI
		SUB DI,2 ; Remove 2 from DI
		CMP DI,SI ; Compare DI and SI
		JE updateLine ; If equal, go to updateLine
		JMP fix ; If not equal, loop
	updateLine:
		CMP currentLine, startDILine ; Compare if the amount of lines done is equal to the line that DI started on
		JE done ; If they are equal, end the loop
		INC currentLine ; If not, add one to currentLine
		JMP loopb ; Go back to reset the registers and loop again
	done:
		POP DI SI ; Restore the values before the call to color
		CALL color ; If equal, go into the color proc
		RET ; Return to the calling proc
	flip ENDP
	
	color PROC ; Color everything not alpha numeric
		MOV AH,00100111b ; Sets the color that everything will be if the character is not alpha
		loopc: ; Create a symbol to loop on
			CMP SI,DI ; Checks if SI is at the last possible position
			JE fin ; Goto fin if the above is true

			MOV AL,ES:[SI] ; Take the character from the screen memory and put it in AL
			
			CMP AL,' ' ; Check if the character is blank
			JE incr ; If it is blank, then jump to incr
			
			CMP AL,'A' ; Compare the character to A
			JL colour ; If it is less than A, then jump to colour
			
			CMP AL,'Z' ; Compare the character to Z
			JLE incr ; If it is between A and Z, go to incr
			
			CMP AL,'a' ; Compare the character to a
			JL colour ; if it is less, than it must be between Z and a, so go to colour
			
			CMP AL,'z' ; Compare the character to z
			JLE incr ; If it is less than z, then go to incr
			
		colour:
			MOV ES:[SI+1],AH ; Changes the color in the screen memory
			JMP incr
		incr:
			ADD SI,2 ; Sets SI to the next thing in the screen memory
			JMP loopc ; Loops back until the compare becomes true
		fin:
			RET ; Return to the calling proc
	color ENDP
MyCode ENDS
END main