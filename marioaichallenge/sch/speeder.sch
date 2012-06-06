initial = hub ;

hub = flat hub , 0.3
		| pipe hub , 0.4
		| coins hub , 0.3;
		
flat = FLAT ;
		
coins = COINS , 0.8
		| COINS coins , 0.2;