# monopoly simulation project
Simple simulation project of monopoly with spring boot and jeasy as rule engine.

## Game & Rules

Each player receives $1500 at the beginning of the game.

- The Go square is the first square on the board.
- The Income Tax square is the 5th square on the board.
- The Luxury Tax square is the 39th square on the board. 
- The GoToPrison square is the 31th square on the board.
- The InJail square is the 11th square on the board.
- The TrainStations are the 6th, the 16th, the 26th and the 36th squares on the board.
- The available streets are the 12th, the 13th, the 15th, the 40th and the 38th squares on the board ("Goodsprings", "Primm", "Freeside", "The Strip" & "Hoover dam").

Consider: The player's balance can be negative. Players do not go bankrupt.

You can play 200 rounds with a maximum of 8 players. 

### Rules
For this demo project I focused with the following rules on the Go, Luxury Tax, Income Tax, GoToPrison, TrainStations, "The Strip" and "Hoover dam" squares to the board:
* When a player passes over or lands on the Go square, the player receives $200.
* When a player lands on the Luxury Tax square, the player pays $75.
* When a player lands on the Income Tax square, the player pays the minimum of $200
  or 10% of their worth
* When a player lands on the GoToPrison square, the player moves to InJail square, pass the Go field, don't get $200, 
  has to pay $50 and can move on with the next move.
* When a player lands on a TrainStation square, the player can buy it if the property available to buy. When not he has 
  to pay a rent. The rent depends on the ownership of the train station. Has the owner one train station as it's own then the rent is $25. On two it's $50, on three it's $100 and on four it's $200.
* When a player lands on "street" square, the player can buy it if the property available to buy. When not he has
  to pay a rent. The rent depends on the ownership. Has the owner all streets of the group then the rent is twice as high as normal.
  The rent for instance are on "the Strip" is $100 and for the "Hoover dam" is $70.

### Costs
| Property          | Prize | Rent | Square | Type          |
|-------------------|-------|------|--------|---------------|
| WestTrainStation  | $200  | $25  | 16     | train station |
| NorthTrainStation | $200  | $25  | 26     | train station |
| EastTrainStation  | $200  | $25  | 36     | train station |
| SouthTrainStation | $200  | $25  | 6      | train station |
| The Strip         | $400  | $100 | 40     | street        |
| Hoover dam        | $350  | $70  | 38     | street        |
| Freeside          | $160  | $12  | 15     | street        |
| Primm             | $140  | $10  | 13     | street        |
| Goodsprings       | $140  | $10  | 12     | street        |

The street names are based on fan made monopoly board "New Vegas".

### Players
Please remember that the possible players only:
dog, iron, battleship, cannon, magic hat, horse, shoe, and car.

## How to run the application?
mvn spring:run

## How to start a game?

1. You can use the swagger-ui for creating and sending of an valid rest request.

http://localhost:8080/swagger-ui/index.html

Please explore that url: /v3/api-docs

2. Or you can send an rest request to the application with your favorite tool.

Rest endpoint: http://localhost:8080/start-monopoly-game

## Sample for the request
{
"playerNames" : ["horse", "car"],
"rounds" : 20
}

## Sample for the response
{
"winner": "car",
"balance": 7200,
"playedRounds": 20
}

## How can I enhance/expand the rules of the game?
This is very easy. You have only implementing new rules and register them.
For more information you can take a look in the package rules and/or the RulesConfig class in the config package.
