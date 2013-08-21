
import org.springframework.jdbc.core.JdbcTemplate
import groovy.json.JsonSlurper

// define the implementation in Groovy
class LogisticRegression  {
	
    JdbcTemplate template
	float threshold
	
	public boolean execute(Object order) {
		
		String sql = "select madlib.logistic(madlib.array_dot(model.coef,ARRAY[" + 
			order.get("orderamount").asDouble() +"," +
			order.get("storeid").asInt() + "," +
			order.get("items").asInt() + "]::double precision[])) as score from model";
		
		float score = template.queryForObject(sql, Float)
		if(score >= threshold) {
			System.out.println("Score: " + score)
			return true
		} else {
			return false
		}
	}
}