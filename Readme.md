# Console Snake Game
## Build project
To build project run from root directory command:
```shell
./gradlew build
```

In the folder `./build/libs` there will be fat-jar executable file.

To start application from windows cmd or power-shell run:
```shell
java -jar SnakeGame-1.0.jar -console windows
```
To start application from unix console run:
```shell
java -jar SnakeGame-1.0.jar -console unix
```

## Snake control

To control the snake use commands:

| Command | Key combination |
|---------|-----------------|
| up      | w + enter       |
| down    | s + enter       |
| left    | a + enter       |
| right   | d + enter       |

## Console Arguments

| Argument | Allowed values | Default | Description                                         |
|----------|----------------|---------|-----------------------------------------------------|
| -console | windows unix   | unix    | Choose the OS system to correctly clear screen      |
| -w       | Int            | 10      | Change width of the board. Must be not less than 3  |
| -h       | Int            | 10      | Change height of the board. Must be not less than 3 |
