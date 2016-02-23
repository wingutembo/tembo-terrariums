# tembo-terrariums
Commit notes on feb 22, 2016
Discovered that my organisms don't die, so put in a little hack. Might be ok though, since my unit test always allows the organism to eat the most it can.

Reproduction design:
- The organism has to have birth size > 0.
- The parent organism size > the offspring organism size so reproduction can't start until parent organism is big enough.
- The parent organism size + the offspring organism size at "birth" < the max organism size otherwise no reproduction
- The parent organism metabolism is computed at size of parent organism size + unborn offspring size. This should have the effect of reducing excess energy to the parent organism
- The parent organism max absorbable energy (the top line in my drawings) is computed at the size of the parent only.
- As soon as the offspring is at size, it is "born"

Design Issues
- World is no fun, only one parent is used to create offspring, but I haven't considered genetics yet.
- If the organism uses all excess energy for reproduction, my design doesn't "age" the organism. So, I think there should be some penalty, since we all know how much children age us!

Genetics
- Things that could be altered from generation to generation:
   - Birth size
   - Max size
   - Max energy
   - 
   
