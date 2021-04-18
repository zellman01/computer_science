MyStack SEGMENT STACK
	DW 256 DUP (?)
MyStack ENDS

MyData SEGMENT
	screenMem EQU 0B800h
	theWord DW "bc"
	endOfWord DW 0 ; Terminator
MyData ENDS

MyCode SEGMENT
	ASSUME CS:MyCode, DS:MyData
	main PROC
	
		MOV AX,MyData ; Move myData segment into AX
		MOV DS,AX ; Move myData segment into DS
	
		MOV AX,screenMem ; Move screen memory into AX
		MOV ES,AX ; Set ES as the screen memory
		
		MOV SI,0 ; Set SI to the start of theWord
		MOV AX,0 ; Set AX as the total
		topConvertLoop:
			MOV BX, theWord+SI ; Set BX to the next character in theWord
			CMP BX,0000000b ; Compare if it is equal to a null terminator
			JE done ; If true, leave the loop
			MOV CX,10 ; Set CX to 10 for multiplication
			MUL CX ; Multiply AX by CX
			SUB BX,'0' ; Make BX into the ASCII code
			ADD AX,BX ; Add it into the total
			ADD SI,2 ; Increase SI by two
			JMP topConvertLoop ; If the above compare was false, loop to the top
		done: ; Label to break out of the loop
			MOV SI,0 ; Reset SI to 0 for the below loop
			MOV DI,(8*80+40)*2 ; Starting position of where to put characters
		displayStart: ; Display the string
			MOV BX,theWord+SI ; Set BX to the next character in theWord
			CMP BX,000000b ; Compare if BX is equal to the end of the string
			JE finish ; If the above is true, finish the program
			MOV ES:[DI],BL ; Display the character
			MOV ES:[DI+2],BH
			INC SI ; Add one to SI
			INC DI ; Add one to DI for coloring
			CMP AX,1000 ; See if the total is greater than 1000
			JG redColor ; If so, then color it red
			JLE yellowColor ; Else, color it yellow
		
			;JMP displayStart ; If the above compare is false, continue the loop
		;MOV ES:[542],
		redColor:
			MOV ES:[DI], BYTE PTR 00000100b ; Color it red
			MOV ES:[DI+2], BYTE PTR 00000100b ; Color it red
			INC DI ; Add one to DI to add the next character
			JMP displayStart ; Go back to the displayStart loop
			
		yellowColor:
			MOV ES:[DI],BYTE PTR 00000110b ; Color it yellow
			MOV ES:[DI+2],BYTE PTR 00000110b ; Color it yellow
			INC DI ; Add one to DI to add the next character
			JMP displayStart ; Go back to the displayStart loop
		finish: ; Finish the given program
			MOV AH,4Ch ; Exit program
			INT 21h ; Return control to the DOS program
	main ENDP
MyCode ENDS
END main