package axon.ui.commands;

import axon.core.Application;
import axon.core.game.command.RegisterGameCommand;
import axon.ui.game.GameCatalog;
import axon.ui.game.GameDTO;
import org.axonframework.eventhandling.replay.ReplayingCluster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ReplayCommand implements CommandMarker {
    @Autowired
    ReplayingCluster replayingCluster;

    @CliCommand("replay")
    public String replay() {
        replayingCluster.startReplay();
        return "replaying done";
    }

}

