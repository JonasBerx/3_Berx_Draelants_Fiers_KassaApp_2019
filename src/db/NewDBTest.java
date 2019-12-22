package db;

import model.article.Article;

public class NewDBTest {



    public static void main(String[] args) {
        Article a = new Article(11, "yey ik werk", "gr1", 69, 10);
        //Test voor inmemory db (subclass)
        ArticleDbInMemory dbInMemory = new ArticleDbInMemory();
        System.out.println(dbInMemory.getAll());
        dbInMemory.add(a);
        System.out.println(dbInMemory.getAll());
        dbInMemory.remove(a);
        System.out.println(dbInMemory.getAll());
        System.out.println(dbInMemory.returnDB().values());

        //Test voor context class ( grote implement class)
        System.out.println("\n");
        ArticleDbContext context = new ArticleDbContext("INMEMORY");
        System.out.println(context.getAll());
        System.out.println(a.saveToString());
        System.out.println(a.toString());

        // EXCEL
        ArticleExcelLoadSave loadSave = new ArticleExcelLoadSave();
        System.out.println(loadSave.load());


    }

}
