package net.ufjnet.combinebackend.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
@Setter
public class StandardError {
	
	private Integer code;
	private LocalDateTime moment;
	private String describe;
	private List<Fields> fields;
	
	@AllArgsConstructor
	@Getter
	@Setter
	public static class Fields {
		private String name;
		private String message;
	}

}
