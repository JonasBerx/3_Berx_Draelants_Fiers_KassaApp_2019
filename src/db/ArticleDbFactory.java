package db;

class ArticleDbFactory {

    DbStrategy create(String type) {
        DbStrategy strategy = null;
        ArticleDbEnum strategyEnum = ArticleDbEnum.valueOf(type.toUpperCase());
        Class dbClass = strategyEnum.getMemoryClass();

        try {
            Object dbObject = dbClass.newInstance();
            strategy = (DbStrategy) dbObject;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return strategy;
    }

}
