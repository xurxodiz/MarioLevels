initial			= hub
				;

# low and high refer to the tension
# low-tension zones are relaxed
# high-tension zones are frenzied

hub				= low hub
				| high hub
				;
				
low				= lelem
				| lelem low , 10
				;
				
lelem			= FLAT spares FLAT , 4
				| FLAT GROUND_DOWN spares FLAT GROUND_UP
				| spare FLAT spare FLAT , 10
				| FLAT blocks FLAT , 3
				;
				
blocks			= block
				| block blocks , 4
				;
				
block			= BLOCK_CE , 6
				| GOOMBA_BLOCK_CE , 6
				| GREENTURTLE_BLOCK_CE , 3
				| BLOCK_EE , 9
				| GOOMBA_BLOCK_EE , 9
				| GREENTURTLE_BLOCK_EE , 9
				| BLOCK_PE , 2
				| GOOMBA_BLOCK_PE , 2
				| REDTURTLE_BLOCK_PE , 1 # easter egg ;)
				;
				
				
spares			= spare
				| spare FLAT spares, 3
				;
				
spare			= spare_enemy , 8
				| spare_block , 7
				| spare_coins , 6
				| GAP , 2
				;
				
spare_enemy 	= GOOMBA , 20
				| SPIKY
				;
				
spare_block		= BLOCK_CE , 5
				| BLOCK_PE , 3
				| BLOCK_EE , 10
				| spare_block FLAT spare_block 
				;
				
spare_coins		= COINS , 4
				| COINS spare_coins
				;

high			= relem
				| relem high , 10
				;
				
relem			= FLAT GROUND_UP spares FLAT GROUND_DOWN , 3
				| FLAT pipe FLAT spare FLAT pipe FLAT, 2
				| cannon , 0.4
				| FLAT GROUND_DOWN raised_blocks FLAT GROUND_UP , 3
				;	
				
raised_blocks	= BLOCK_EE , 3
				| BLOCK_PE
				| BLOCK_CE , 2
				| BLOCK_EE_GOOMBA , 3
				| BLOCK_PE_GOOMBA
				| BLOCK_CE_GOOMBA , 2
				| raised_blocks raised_blocks , 7
				;
				
pipe			= PIPE
				| PIPE_PIRANHA, 3
				;
				
cannon			= FLAT spare_enemy FLAT spare_enemy FLAT precannon CANNON
				;	
				
# gaps before a cannon look awesome
precannon		= FLAT , 12
				| GAP 
				;