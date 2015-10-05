package io.thappx.data.entity;

public class Configuration {
	private final String QUALITY_DESIRED    = "w780";
	private final String QUALITY_ORIGINAL   = "original";

	private String posterBaseUrl, backdropBaseUrl;

	Images images;

	static class Images {
		String secure_base_url;
		String[] backdrop_sizes;
		String[] poster_sizes;
	}

	public String getBaseUrl() {
		return images.secure_base_url;
	}

	public String[] getBackdropSizes() {
		return images.backdrop_sizes;
	}

	public String[] getPosterSizes() {
		return images.poster_sizes;
	}

	private String configurePosterBaseUrl() {
		if (posterBaseUrl == null) {
			String url;

			String imageQuality = "";
			url = getBaseUrl();

			for (String quality : getPosterSizes()) {

				if (quality.equals(QUALITY_DESIRED)) {
					imageQuality = QUALITY_DESIRED;
					break;
				}
			}

			if (imageQuality.equals(""))
				imageQuality = QUALITY_ORIGINAL;

			url += imageQuality;
			posterBaseUrl = url;
			return url;
		} else return posterBaseUrl;
	}

	private String configureBackdropBaseUrl() {
		if (backdropBaseUrl == null) {
			String url;

			String imageQuality = "";
			url = getBaseUrl();

			for (String quality : getPosterSizes()) {

				if (quality.equals(QUALITY_DESIRED)) {
					imageQuality = QUALITY_DESIRED;
					break;
				}
			}

			if (imageQuality.equals(""))
				imageQuality = QUALITY_ORIGINAL;

			url += imageQuality;
			backdropBaseUrl = url;
			return url;
		} else return backdropBaseUrl;
	}

	public String getFullPosterPath(String pPosterPath) {
		return configurePosterBaseUrl() + pPosterPath;
	}

	public String getFullBackdropPath(String pBackdropPath) {
		return configureBackdropBaseUrl() + pBackdropPath;
	}
}
