package database;

import excel.ExcelPlugin;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.Article;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
* @Author Jonas Berx
* @Version 1.1
* */
public class ExcelAdapter implements LoadSaveStrategy {
    private ArticleDbInMemory dbInMemory;
    private ExcelPlugin plugin;
    private File articleFile;



    ExcelAdapter() {
        plugin = new ExcelPlugin();
        dbInMemory = new ArticleDbInMemory();
        articleFile = new File("src/bestanden/articles.xls");


    }

    @Override
    public void load() {
        try {
            for (ArrayList<String> a : plugin.read(articleFile)) {
                //For testing purpose
                System.out.println(a.get(0) + "," + a.get(1) + "," + a.get(2) + "," + a.get(3) + "," + a.get(4));

                Article newArticle = new Article(Integer.parseInt(a.get(0)),
                        a.get(1), a.get(2), Double.parseDouble(a.get(3)), Integer.parseInt(a.get(4)));
                dbInMemory.addToMap(newArticle);

            }
        } catch (BiffException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(ArrayList<Article> articles) throws IOException {
        ArrayList<ArrayList<String>> result= new ArrayList<>();
        for (Article article : articles) {
            ArrayList<String> articl = new ArrayList<>();
            articl.add(String.valueOf(article.getArticleCode()));
            articl.add(article.getArticleName());
            articl.add(article.getGroup());
            articl.add(String.valueOf(article.getPrice()));
            articl.add(String.valueOf(article.getQuantity()));
            result.add(articl);
        }

        try {
            plugin.write(articleFile, result);
        } catch (WriteException | BiffException e) {
            e.printStackTrace();
        }
    }
//    I stole this :O
//    Leave this for now plox
//
//    protected void saveFile(ArrayList<Product> products) {
//        ArrayList<ArrayList<String>> result= new ArrayList<>();
//        for (Product p:products) {
//            ArrayList<String> product = new ArrayList<>();
//            product.add(String.valueOf(p.getId()));
//            product.add(p.getName());
//            product.add(p.getGroup());
//            product.add(String.valueOf(p.getPrice()));
//            product.add(String.valueOf(p.getStock()));
//            result.add(product);
//        }
//        try {
//            excelPlugin.write(new File(super.getPath()),result);
//        } catch (BiffException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (WriteException e) {
//            e.printStackTrace();
//        }
//    }



}
