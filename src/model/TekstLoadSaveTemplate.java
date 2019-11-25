package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
* @Author Jonas Berx
* Implemented Template Design pattern
* */

public abstract class TekstLoadSaveTemplate {
    String path;
    TekstLoadSaveTemplate() {}

    abstract void load();

    abstract void save();

}
