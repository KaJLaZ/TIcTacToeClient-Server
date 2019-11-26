package Dar.TipTapToeClientServer.Controllers;

import Dar.TipTapToeClientServer.core.Coordinate;
import Dar.TipTapToeClientServer.core.Game;
import Dar.TipTapToeClientServer.core.GameBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/game commands")
@Api(value = "game commands", description = "list of game commands")
public class GameController {
    Game game;

    @RequestMapping(method = RequestMethod.PUT, value = "/New Game")
    public void startNewGame(char firPlaySymb, char secPlaySymb) {
        game = new GameBuilder().
                                 withFirPlaySymb(firPlaySymb).
                                 withSecPlaySymb(secPlaySymb).
                                 build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/Show field")
    public char[][] ShowField() {
        return game.getField();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/Make a move")
    public HttpStatus turn(int row, int column) {
        try{
            if(!game.step(new Coordinate(row, column))){
                    return HttpStatus.RESET_CONTENT;
            }
        }
        catch (Game.IncorectInputException ex){
            return HttpStatus.BAD_REQUEST;
        }

        return HttpStatus.OK;
    }
}
