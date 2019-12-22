package view.jfx.client;

import javafx.scene.layout.BorderPane;
import model.DomainFacade;

public class Main extends BorderPane {
    private Overview overview;


    public Main() {
        overview = new Overview();
        this.setCenter(overview);
    }

    public Overview getOverview() {
        return overview;
    }
}
