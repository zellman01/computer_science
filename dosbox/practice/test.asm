MyStack SEGMENT STACK
	DW 256  DUP (?)
MyStack ENDS

MyData SEGMENT
	ScreenMem EQU 0B800h
MyData ENDS

MyCode SEGMENT
	ASSUME DS:MyData, CS:MyCode
	main PROC
		MOV AX,MyData
		MOV DS,AX
	
		MOV AX,ScreenMem
		MOV ES,AX
		
		
		MOV DI, 0
		
		displayLoop:
			CMP DI,10000
			JE done
			MOV ES:[DI],BYTE PTR 'A'
			INC SI
			ADD DI,2
			
			JMP displayLoop
		done:
		MOV     AH, 4Ch                ; DOS interrupt to return control to DOS
		INT     21h
	Main ENDP
		
MyCode ENDS
END main