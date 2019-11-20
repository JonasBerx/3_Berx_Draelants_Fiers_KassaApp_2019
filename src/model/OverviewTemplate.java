package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public abstract class OverviewTemplate {
    private List<Opdracht> opdrachten = new ArrayList<>();
    private File inTeLezen;
    private Scanner scanFile;

    public OpdrachtDatabank(String filepath) {
        try {
            this.inTeLezen = new File(filepath);
            this.scanFile = new Scanner(inTeLezen);
            leesIn();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found");
        }
    }

    public OpdrachtDatabank() {
    }

    private void leesIn() {
        while (scanFile.hasNextLine()) {
            Scanner scannerLijn = new Scanner(scanFile.nextLine());
            scannerLijn.useDelimiter("\t");
            int Id = Integer.parseInt(scannerLijn.next());
            String vraag = scannerLijn.next();
            String antwoord = scannerLijn.next();
            boolean hoofdletterGevoelig = Boolean.parseBoolean(scannerLijn.next());
            String hint = scannerLijn.next();
            String categorie = scannerLijn.next();
            String auteur = scannerLijn.next();
            opdrachten.add(new Opdracht(Id, vraag, antwoord, hoofdletterGevoelig, hint, categorie, auteur));
        }
    }

    public List<Opdracht> getOpdrachten() {
        return opdrachten;
    }

    public List<Opdracht> getOpdrachtenGesorteerdOpCategorie() {
        Collections.sort(opdrachten);
        return opdrachten;
    }

    public List<Opdracht> getOpdrachtenGesorteerdOpCategorieEnId() {
        Collections.sort(opdrachten, new ComperatorByCategorieEnId());
        return opdrachten;
    }


    public void voegToe(Opdracht o) {
        if (o == null) {
            throw new IllegalArgumentException("");
        } else {
            opdrachten.add(o);
        }
    }
}
