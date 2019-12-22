package db;

import excel.ExcelPlugin;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.article.Article;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ArticleExcelLoadSave implements LoadSaveStrategy {
    private ExcelPlugin plugin;
    private File articleFile = new File("src/bestanden/articles.xls");
    private ArrayList<Article> articles;

    ArticleExcelLoadSave() {
        plugin = new ExcelPlugin();
        articles = new ArrayList<>();

    }

    @Override
    public ArrayList<Article> load() {
        try {
            for (ArrayList<String> a : plugin.read(articleFile)) {
                //For testing purpose
//                System.out.println(a.get(0) + "," + a.get(1) + "," + a.get(2) + "," + a.get(3) + "," + a.get(4));

                Article newArticle = new Article(Integer.parseInt(a.get(0)),
                        a.get(1), a.get(2), Double.parseDouble(a.get(3)), Integer.parseInt(a.get(4)));
                articles.add(newArticle);

            }
        } catch (BiffException | IOException e) {
            e.printStackTrace();
        }
        System.out.println(articles.size());
        return articles;
    }

    @Override
    public void save(ArrayList<Article> articles) {
        ArrayList<ArrayList<String>> result= new ArrayList<>();
        for (Article article : articles) {
            ArrayList<String> articl = new ArrayList<>();
            articl.add(String.valueOf(article.getArticleCode()));
            articl.add(article.getName());
            articl.add(article.getGroup());
            articl.add(String.valueOf(article.getPrice()));
            articl.add(String.valueOf(article.getQuantity()));
            result.add(articl);
        }

        try {
            plugin.write(articleFile, result);
        } catch (WriteException | BiffException | IOException e) {
            e.printStackTrace();
        }
    }
}
