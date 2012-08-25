initial			= hub
				;

# low and high refer to the tension
# low-tension zones are relaxed
# high-tension zones are frenzied

hub				= low hub
				| high hub
				;
				
low				= lelem
				| lelem low , 2
				;
				
high			= helem
				| helem high , 2
				;
				
go_up			= FLAT GROUND_UP
				;
				
go_down			= FLAT GROUND_DOWN
				;
				
lelem			= FLAT spares FLAT , 4
				| go_up spares go_down , 3
				| FLAT down_blocks FLAT , 3
				| go_up coins_blocks go_down , 2
				;
				
helem			= go_down spares go_up , 2
				| go_down up_blocks go_up , 2
				| FLAT pipe FLAT spares FLAT pipe FLAT, 2
				| cannon , 0.75
				;	
				
spares			= spare
				| spare FLAT spares, 2
				;
				
spare			= enemy , 8
				| block , 7
				| COINS , 6
				| GAP , 2
				;
				
enemy 			= GOOMBA , 20
				| SPIKY
				;
				
coins_blocks	= blocks
				| coins
				;
				
coins			= COINS
				| COINS coins , 3.5
				;
				
block			= BLOCK_EE , 9
				| BLOCK_CE , 6
				| BLOCK_PE , 2
				;	
				
blocks			= block
				| block blocks , 3.5
				;

# blocks that may have enemies on top
up_blocks		= block
				| enemy_block
				| block up_blocks , 3.5
				| enemy_block up_blocks , 3.5
				;
			
# blocks that may have enemies underneath	
down_blocks		= block
				| block_enemy
				| block down_blocks , 3.5
				| block_enemy down_blocks , 3.5
				;
				
block_enemy		= GOOMBA_BLOCK_EE , 9
				| GOOMBA_BLOCK_CE , 6
				| GOOMBA_BLOCK_PE , 2
				| GREENTURTLE_BLOCK_EE , 9
				| GREENTURTLE_BLOCK_CE , 3
				| REDTURTLE_BLOCK_PE , 1 # easter egg ;)
				;
				
enemy_block		= enemy_block
				| enemy_block enemy_blocks , 3.5
				;
				
enemy_block		= BLOCK_EE_GOOMBA , 3
				| BLOCK_CE_GOOMBA , 2
				| BLOCK_PE_GOOMBA
				;
				
pipe			= PIPE
				| PIPE_PIRANHA, 2
				;
				
cannon			= FLAT spare FLAT spare_enemy FLAT precannon CANNON
				;	
				
# gaps just before a cannon look awesome
precannon		= FLAT , 12
				| GAP 
				;