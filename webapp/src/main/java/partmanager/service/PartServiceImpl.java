package partmanager.service;

import partmanager.DAO.PartDAO;
import partmanager.model.Part;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PartServiceImpl implements PartService {

    private PartDAO partDAO;

    public void setPartDAO(PartDAO partDAO) {
        this.partDAO = partDAO;
    }

    @Override
    @Transactional
    public void addPart(Part part) {
        Part existingPart = findByNM(part.getCompName(), part.getManuf());
        if (existingPart == null) {
            partDAO.addPart(part);
            return;
        }
        existingPart.setQuant(existingPart.getQuant()+part.getQuant());
        update(existingPart);
    }

    @Override
    @Transactional
    public void update(Part part) {
        partDAO.update(part);
    }

    @Override
    @Transactional
    public void delete(int id) {
        partDAO.delete(id);
    }

    @Override
    @Transactional
    public List<Part> getAll() {
        return partDAO.list();
    }

    @Override
    @Transactional
    public List<Part> getNess() {
        List<Part> allList = partDAO.list();
        List<Part> res = new ArrayList<>();
        for (Part p: allList) {
            if (p.isNess())
                res.add(p);
        }
        return res;
    }

    @Override
    @Transactional
    public List<Part> getUnNess() {
        List<Part> allList = partDAO.list();
        List<Part> res = new ArrayList<>();
        for (Part p: allList) {
            if (!p.isNess())
                res.add(p);
        }
        return res;
    }

    @Override
    @Transactional
    public Part get(int id) {
        return partDAO.get(id);
    }

    @Override
    @Transactional
    public List<Part> find(String name) {

        List<Part> list = partDAO.list();
        ArrayList<Part> result = new ArrayList<>();
        for (Part p: list) {
            if (p.getCompName().equals(name))
            result.add(p);
        }
        return result;
    }

    @Transactional
    public Part findByNM(String name, String manuf) {

        List<Part> list = partDAO.list();
        for (Part p: list) {
            if (p.getCompName().equals(name)&&p.getManuf().equals(manuf))
                return p;
        }
        return null;
    }

    @Override
    @Transactional
    public int quantComps() {
        List<Part> list = getNess();
        int min = Integer.MAX_VALUE;
        for (Part p: list) {
            if (p.getQuant()<min)
                min = p.getQuant();
        }
        return min;
    }
}
