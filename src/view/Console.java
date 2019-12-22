package view;

import view.console.Start;

/**
 * Dummy console view to demonstrate
 * strategy pattern for views.
 */
public class Console implements ViewStrategy {
    public void main(String[] args) {
        Start.start();
    }
}
