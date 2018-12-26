package partmanager.DAO;

import partmanager.model.Part;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PartDAOImpl implements PartDAO {


   private SessionFactory factory;

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void addPart(Part part) {
        Session session = factory.getCurrentSession();
        session.persist(part);
    }

    @Override
    public void update(Part part) {
        Session session = factory.getCurrentSession();
        session.update(part);
    }

    @Override
    public void delete(int id) {
        Session session = factory.getCurrentSession();
        Part part = (Part)session.load(Part.class, id);
        if (part!=null) session.delete(part);
    }

    @Override
    public Part get(int id) {
        Session session = factory.getCurrentSession();
        Part part = (Part)session.load(Part.class, id);

        return part;
    }

    @Override
    public List<Part> list() {
        Session session = factory.getCurrentSession();

        List<Part> list = (List<Part>)session.createQuery("FROM Part").list();

        return list;
    }
}
