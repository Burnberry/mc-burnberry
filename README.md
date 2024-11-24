# mc-burnberry
### plan
- arrow inventory
  - Update arrow in player inventory
  - update arrow count on bow name?
  + CustomArrows?

- Defense
  - Connect to resource game
  - Death condition
  - Crafting 
  - upgrades/constraints:
    - Limited number of base arrows (4)
      - regenerates near base
      - upgrade max number/regen speed
      - upgrade chance arrow replenish on hit
      - upgrade damage/effects
      - Special arrows? Should be easy to switch
        - Consumables initially?
     - health regen only near base
       - Very slow base 
     - repair base
  - enemies
    - weakling zombie
      - small, insta kill, somewhat fast
    - zombie
      - normal, slow
    - zombie grunt
      - slightly bigger, tanky
    - tiny ghasts
    - cubes with extra splits?
    - shulkers?
    - evoker?
    - giant vexes?
  - bosses:
    - They spawn from past behing the first wall, first big boss breaks wall
    - Giant golem
      - extremely tanky
      - stops a few blocks from the entrance blocking arrows
    - Riders
      - tiny rider rides on a ravager/horse
      - Hard to hit rider
      - Speeds up, needs to be shot to slow down
    - Lich
      - large crowned skeleton
      - Spawns skeletons

- Resource Game
  - Woodchopping 2
    - Really boring, how to make interesting?
      - if not possible, make it the easiest to automate
      - foraging/hunting instead and add extra things to do
    - Tree with main health
      - Hitting the tree drops leaves
      - Leaves can turn into extra loot
  - mining 2
    - random generation (research noise generation)
    - random ore spawns
  - farming
    - food grows with ticks
    - food needed for minions
  - smelting tech
    - coal needed for smelting
    - furnaces need to be built
    - upgrades for blast furnace + smokers

### extras
- custom bow charge
  - Not possible with bows
  - Custom bow mechanics with quickcharge crossbows onload?
    - EntityLoadCrossbowEvent

- arrow selection
  - Use radial wheel using itemdisplay entities (to research)

- hit sounds
  - custom projectiles
  - Different sounds per projectile

### long term features
- Players leaving / rejoining