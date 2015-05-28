package stats;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;

@Api(value = "WebCalls", description = "Web Service Calls")
@RestController
public class WebCallsController {

	@ResponseBody
    @RequestMapping(value="/getGamesByDate", method=RequestMethod.GET)
    public String getGameByDate(@RequestParam(value="date") String date) {
        WebCalls returnStuff = new WebCalls(date);
        return returnStuff.getStats();
    }
    
	@ResponseBody
    @RequestMapping(value= "/increment", method=RequestMethod.GET)
    public int increment(@RequestParam(value="num", defaultValue="1") int numb){
    	return numb+1;
    }
    
}