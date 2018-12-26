package partmanager.DAO;

import partmanager.model.Part;

import java.util.List;

public interface PartDAO {
   void addPart(Part part);
   void update(Part part);
   void delete(int id);
   Part get(int id);
   List<Part> list();
}
