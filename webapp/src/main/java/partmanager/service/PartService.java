package partmanager.service;

import partmanager.model.Part;

import java.util.List;

public interface PartService {
    void addPart(Part part);
    void update(Part part);
    void delete(int id);
    List<Part> getAll();
    List<Part> getNess();
    List<Part> getUnNess();
    List<Part> find(String name);
    Part get(int id);
    int quantComps();
}
