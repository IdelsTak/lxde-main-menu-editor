/*
 * The MIT License
 *
 * Copyright 2022 Hiram K..
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.idelstak.menueditor.view.controllers;

import com.github.idelstak.menueditor.model.Categories;
import com.github.idelstak.menueditor.model.Category;
import com.github.idelstak.menueditor.model.DesktopEntryFiles;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/** @author Hiram K. */
public class MainController {
  private static final Logger LOG = Logger.getLogger(MainController.class.getName());

  @FXML private ListView<Category> categoriesList;

  @FXML
  void initialize() {
    var entryFiles =
        //        new DesktopEntryFiles(System.getProperty("user.home") +
        // "/.local/share/applications");
        new DesktopEntryFiles("/usr/share/applications/");
    //        new DesktopEntryFiles("/usr/local/share/applications/");

    var categories = new Categories(entryFiles);

    categoriesList.setCellFactory(
        (param) -> {
          return new ListCell<>() {
            @Override
            protected void updateItem(Category item, boolean empty) {
              super.updateItem(item, empty);
              super.setText(empty ? null : item.getName());
            }
          };
        });

    categoriesList.setItems(
        FXCollections.observableArrayList(
            StreamSupport.stream(categories.spliterator(), false)
                .sorted()
                .collect(Collectors.toList())));
  }
}
