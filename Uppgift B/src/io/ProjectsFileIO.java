package io;

import model.Project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectsFileIO {

    /**
     * Call this method before the application exits, to store the users and projects,
     * in serialized form.
     */
    public static void serializeToFile(File file, List<Project> data) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(data);
        }
    }

    /**
     * Call this method at startup of the application, to deserialize the users and
     * projects from the specified file.
     */
    @SuppressWarnings("unchecked")
    public static List<Project> deSerializeFromFile(File file) throws IOException, ClassNotFoundException {
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Project>) in.readObject();
        }
    }

    private ProjectsFileIO() {
    }
}