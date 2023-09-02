package PetStore;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetStoreLombok {
	private Integer id;
	private Category category;
	private String name;
	private List<String> photoUrls;
	private String status;
	private List<Tag> tags;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Category {
		private Integer id;
		private String name;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Tag{
		private Integer id;
		private String name;
	}

}
