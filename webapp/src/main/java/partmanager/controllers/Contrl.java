package partmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import partmanager.model.Part;
import partmanager.service.PartService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Contrl {

    private PartService partService;
    private int currPage = 0;
    private String sorting = "full";

    @Autowired
    @Qualifier(value = "servicePart")
    public void setPartService(PartService partService) {
        this.partService = partService;
    }

    @RequestMapping("/")
    public String redirect(){
        return "redirect:/st";
    }
    @RequestMapping("/st")
    public String showTable(@RequestParam(value = "paging", defaultValue = "no")String page, @RequestParam(value = "sort", defaultValue = "const") String sort, Model model){

        if (!sort.equals("const")) {
            sorting = sort;
            currPage = 0;
        }

        String title = null;

        List<Part> list=null;
        if (sorting.equals("full")) {
            list = partService.getAll();
            title = "Все детали";
        }
        if (sorting.equals("ness")) {
            list = partService.getNess();
            title = "Необходимые для сборки детали";
        }
        if (sorting.equals("unness")) {
            list = partService.getUnNess();
            title = "Опционные детали";
        }

        List<Part> viewList = new ArrayList<Part>();
        int pagesCount = pageCount(list);
        if (page.equals("no")) {
            for (int i = 0; i < 10; i++) {
                if (i<list.size())
                viewList.add(list.get(i));
                else break;
            }
        }
        if (page.equals("next")){
            if (currPage<pagesCount-1)
                currPage++;
            for (int i = currPage*10; i < currPage*10+10; i++) {
                if (i<list.size())
                    viewList.add(list.get(i));
                else break;
            }
        }
        if (page.equals("previous")){
            if (currPage>0)
                currPage--;
            for (int i = currPage*10; i < currPage*10+10; i++) {
                if (i<list.size())
                    viewList.add(list.get(i));
                else break;
            }
        }
        model.addAttribute("parts",viewList);
        int minNumber = partService.quantComps();
        model.addAttribute("number", minNumber);
        model.addAttribute("title", title);

        return "form";
    }




    @RequestMapping("/add")
    public String addPart(Model model){
        return "addform";
    }

    @RequestMapping("/search")
    public String search(@RequestParam(value = "detailname", defaultValue = "empty")String part, Model model){

        List<Part> parts = partService.find(part);

        model.addAttribute("details", parts);
        return "search";
    }

    @RequestMapping("/handleadd")
    public String adding(@RequestParam("datailname") String name, @RequestParam("manufactor") String manuf, @RequestParam("ness") String ness, @RequestParam("count") String count, Model model){
        sorting = "full";
        currPage = 0;
        Part newPart = new Part();
        newPart.setCompName(name);
        newPart.setManuf(manuf);
        boolean nes = ness.equals("true");
        newPart.setNess(nes);
        int iCount;
        try {
            iCount = Integer.parseInt(count);
        }
        catch (Exception e){
            iCount = 0;
        }
        newPart.setQuant(iCount);
        partService.addPart(newPart);
     return "redirect:/st";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Part part = partService.get(id);
        model.addAttribute("part", part);
        return "editform";
    }

    @RequestMapping("/handledit/{id}")
    public String editing(@PathVariable Integer id, @RequestParam("datailname") String name, @RequestParam("manufactor") String manuf,
                          @RequestParam("ness")String ness, @RequestParam("count") String quant, Model model){
        Part part = partService.get(id);
        part.setCompName(name);
        part.setManuf(manuf);
        part.setNess(ness.equals("true"));
        try {
            part.setQuant(Integer.valueOf(quant));
        } catch (NumberFormatException e) {
            part.setQuant(0);
        }
        partService.update(part);
        sorting = "full";
        currPage = 0;
        return  "redirect:/st";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model){
        partService.delete(id);
        sorting = "full";
        currPage = 0;
        return  "redirect:/st";
    }

    private int pageCount(List<Part> list){
        int num = list.size()/10;
        if (list.size()%10==0)
            return num;
        else return ++num;
    }


}
