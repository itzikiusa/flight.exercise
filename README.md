# flight.exercise

small note, if i had an additional half hour i would split the repo into different modules, it is just more to me like that

regarding the cache - didn't know if I can use external libraries(Guava Cache/ or Apache PassiveMap)

tests implemented, however, validation
as i'm already receiveing the values as their type, no validation made.

given a little more time, i would extract some values given filter, just to validate if they are in DB or not, and return the proper error

currently all responses are 200(except exceptions, which will return 500);
