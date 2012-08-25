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
				
lelem			= FLAT rows FLAT , 4
				| go_up coins go_up rows go_down coins go_down , 3
				| go_up coins_blocks go_down , 2
				;
				
helem			= go_down zigzag_blocks FLAT go_up , 4
				| pipe rows pipe, 2
				| cannon , 0.5
				;	
				
rows			= zigzag_blocks , 10
				| coins_blocks  , 6
				| spares , 4
				;
				
enemy		 	= GOOMBA , 20
				| GREENTURTLE , 15
				| REDTURTLE , 5
				;
				
coins			= COINS
				| COINS coins , 2
				;			
				
block			= BLOCK_EE , 4
				| BLOCK_CC
				| BLOCK_CE , 9
				| BLOCK_PE , 3
				;

blocks			= block
				| block blocks , 4
				;
				
coins_blocks	= blocks_enemies , 2
				| coins
				;
				
block_enemy		= GOOMBA_BLOCK_EE , 5
				| GOOMBA_BLOCK_CE , 7
				| GOOMBA_BLOCK_CC , 6
				| GREENTURTLE_BLOCK_EE , 3
				| GREENTURTLE_BLOCK_CC , 2
				| GREENTURTLE_BLOCK_CE , 4
				| REDTURTLE_BLOCK_PE , 0.3 # easter egg ;)
				;
				
blocks_enemies	= block_enemy
				| block_enemy blocks_enemies , 4
				| FLAT blocks_enemies , 0.5
				;
				
enemy_block		= BLOCK_EE_GOOMBA
				| BLOCK_CE_GOOMBA , 3
				| BLOCK_CC_GOOMBA , 2
				| BLOCK_PE_GOOMBA , 2
				;
				
spares			= spare
				| spare spares , 5
				;
				
spare			= block_enemy
				| enemy_block
				| block
				| COINS
				| enemy
				| GAP , 0.2
				| pipe , 0.1
				;
				
zigzag_blocks	= all_block
				| all_block zigzag_blocks , 4
				;
all_block		= enemy_block , 3
				| block_enemy , 3
				| block
				| FLAT , 0.5
				;
			
pipe			= PIPE , 3
				| PIPE_PIRANHA
				;
				
cannon			= FLAT rows FLAT precannon CANNON
				;	
				
# gaps just before a cannon look awesome
precannon		= FLAT , 7
				| GAP 
				;