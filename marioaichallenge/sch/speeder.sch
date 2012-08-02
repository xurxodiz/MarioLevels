initial = hub ;

hub = flat hub , 0.3
		| GROUNDUP pipe GROUNDDOWN hub , 15
		| coins hub , 0.3
		| GOOMBABLOCKCC hub
		| BLOCKPEREDTURTLE hub
		| STAIRSUP STAIRSUPUP hub
		| STAIRSDOWNDOWN STAIRSDOWN hub
		;
		
pipe 			= PIPE , 2
				| PIPEPIRANHA 
				;
		
flat = FLAT
	 ;
		
coins = COINS , 0.8
		| COINS coins , 0.2
		;