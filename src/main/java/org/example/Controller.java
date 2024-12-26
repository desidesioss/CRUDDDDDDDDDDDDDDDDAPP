package org.example;

import com.crudapp.dao.EntityDAO;
import com.crudapp.model.Entity;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class EntityController {
    private EntityDAO entityDAO = new EntityDAO();

    public VBox createEntityListView() {
        VBox listView = new VBox();
        List<Entity> entities = entityDAO.getAllEntities();

        for (Entity entity : entities) {
            Button btn = new Button(entity.getName());
            btn.setOnAction(e -> showEntityDetails(entity));
            listView.getChildren().add(btn);
        }
        return listView;
    }

    public void showEntityDetails(Entity entity) {
        Text details = new Text("ID: " + entity.getId() + "\n" +
                "Name: " + entity.getName() + "\n" +
                "Description: " + entity.getDescription() + "\n" +
                "Created At: " + entity.getCreatedAt() + "\n" +
                "Updated At: " + entity.getUpdatedAt());
        // You can add editing functionality here
    }

    public VBox createTopBar() {
        Button addButton = new Button("Add Entity");
        addButton.setOnAction(e -> showAddEntityForm());

        VBox top
