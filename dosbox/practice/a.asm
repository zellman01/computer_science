; Sample test

MyStack SEGMENT STACK
	DW 256 DUP (?)
MyStack ENDS

MyData SEGMENT
	ScreenMem EQU 0B800h
MyData ENDS

MyCode SEGMENT
	ASSUME DS:MyData
	
	main PROC
		MOV   AX,MyData
		MOV   DS,AX
		
		MOV   AX,ScreenMem
		MOV   ES,AX
		
		MOV   ES:[(3*80 + 40)*2], BYTE PTR "P"
		 MOV     ES:[((3*80 + 40)*2)+1], BYTE PTR 01111100b
		MOV   ES:[((4*80 + 40)*2)], BYTE PTR "A"
		MOV     ES:[((4*80 + 40)*2)+1], BYTE PTR 01111100b
		MOV   ES:[((5*80 + 40)*2)], BYTE PTR "N"
		MOV     ES:[((5*80 + 40)*2)+1], BYTE PTR 01111100b
		MOV   ES:[((6*80 + 40)*2)], BYTE PTR "D"
		MOV     ES:[((6*80 + 40)*2)+1], BYTE PTR 01111100b
		MOV   ES:[((7*80 + 40)*2)], BYTE PTR "E"
		MOV     ES:[((7*80 + 40)*2)+1], BYTE PTR 01111100b
		MOV   ES:[((8*80 + 40)*2)], BYTE PTR "M"
		MOV     ES:[((8*80 + 40)*2)+1], BYTE PTR 01111100b
		MOV   ES:[((9*80 + 40)*2)], BYTE PTR "I"
		MOV     ES:[((9*80 + 40)*2)+1], BYTE PTR 01111100b
		MOV   ES:[((10*80 + 40)*2)], BYTE PTR "C"
		MOV     ES:[((10*80 + 40)*2)+1], BYTE PTR 01111100b
		
		MOV   AH,4Ch
		INT   21h
	main ENDP
MyCode ENDS

END main