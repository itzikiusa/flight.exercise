# flight.exercise

small note, if i had an additional half hour i would split the repo into different modules, it is just more to me like that

regarding the cache - didn't know if I can use external libraries(Guava Cache/ or Apache PassiveMap)

tests implemented, however, validation
as i'm already receiveing the values as their type, no validation made.

given a little more time, i would extract some values given filter, just to validate if they are in DB or not, and return the proper error

currently all responses are 200(except exceptions, which will return 500);


available ticket format - ticket numbers from 0-999 are taken, all other will return true

baggage checkin checker -  destination numbers between 0-999, baggage id - 'baggage_' + num, as the destination id would be the id inserted in num, meaning the couples are - [0,baggage_0],[1,baggage_1]....

coupon applyer - couponse available are from 0-999, as calculation for coupon is (num%5*10) + 10
meanning that for 0, the discount is 10%, for 1 the discount is 20% and so on


