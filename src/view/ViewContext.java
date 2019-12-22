package view;

public class ViewContext {
    private ViewStrategy view;

    public ViewContext(ViewStrategy view) {
        this.view = view;
    }

    public ViewStrategy getView() {
        return view;
    }

    public void main(String[] args) {
        view.main(args);
    }
}
