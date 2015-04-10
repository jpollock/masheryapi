package com.mashery.pm.api.sdk.domain;

import java.util.HashMap;
import java.util.List;

import com.mashery.pm.api.sdk.domain.Key;
import com.mashery.pm.api.sdk.domain.Member;
import com.mashery.pm.api.sdk.domain.Package_key;
import com.mashery.pm.api.sdk.domain.Plan;

public class Application {
	private String preferred_protocol;  //maxLength:32, format: , defaultValue=, optional=yes, readonly=no, createonly=no, queryable=yes, sortable=no
	private String tags;  //maxLength:255, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String external_id;  //maxLength:255, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=yes
	private String status;  //maxLength:16, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String ads_system;  //maxLength:64, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Integer group_id;  //maxLength:64, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String type;  //maxLength:255, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String uri;  //maxLength:255, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String object_type;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private Integer id;  //maxLength:32, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=no
	private String username;  //maxLength:255, format: , defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=yes
	private String preferred_output;  //maxLength:32, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String how_did_you_hear;  //maxLength:255, format: , defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String created;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=yes
	private String updated;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=yes, createonly=no, queryable=no, sortable=yes
	private String description;  //maxLength:20, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String usage_model;  //maxLength:32, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String developer_group_handle;  //maxLength:255, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String name;  //maxLength:127, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=yes
	private Boolean is_packaged;  //maxLength:127, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Boolean ads;  //maxLength:127, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private String notes;  //maxLength:127, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private Boolean commercial;  //maxLength:127, format: date-time, defaultValue=, optional=no, readonly=no, createonly=no, queryable=no, sortable=no
	private List<Key> keys;
	private List<Package_key> package_keys;
	private List<Package> packages;
	private List<Plan> plans;
	private Member member;

	// rovi
	private String audio_samples;
	private String brazil_tv_data_checkbox;
	private String experience_package_enable;
	private String geography_asia_checkbox;
	private String geography_canada_checkbox;
	private String geography_europe_checkbox;
	private String geography_lamerica_checkbox;
	private String geography_US_checkbox;
	private String geography_ww_checkbox;
	private String getty_enable;
	private String movie_enabled;
	private String music_enabled;
	private String music_movie_metadata_checkbox;
	private String net5_enabled;
	private String sbs6_enabled;
	private String TV_listings_checkbox;
	private String variants_enabled;
	private String variants_filter;
	private String veronica_enabled;
	private String videodetective;

	public Application() {
	}

	public String getPreferred_protocol() {
		return this.preferred_protocol;
}
	public void setPreferred_protocol(String preferred_protocol) {
		this.preferred_protocol = preferred_protocol;
	}

	public String getTags() {
		return this.tags;
}
	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getExternal_id() {
		return this.external_id;
}
	public void setExternal_id(String external_id) {
		this.external_id = external_id;
	}

	public String getStatus() {
		return this.status;
}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getAds_system() {
		return this.ads_system;
}
	public void setAds_system(String ads_system) {
		this.ads_system = ads_system;
	}

	public Integer getGroup_id() {
		return this.group_id;
}
	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	public String getType() {
		return this.type;
}
	public void setType(String type) {
		this.type = type;
	}

	public String getUri() {
		return this.uri;
}
	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getObject_type() {
		return this.object_type;
}
	public void setObject_type(String object_type) {
		this.object_type = object_type;
	}

	public Integer getId() {
		return this.id;
}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPreferred_output() {
		return this.preferred_output;
}
	public void setPreferred_output(String preferred_output) {
		this.preferred_output = preferred_output;
	}

	public String getUpdated() {
		return this.updated;
}
	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getCreated() {
		return this.created;
}
	public void setCreated(String created) {
		this.created = created;
	}

	public String getHow_did_you_hear() {
		return this.how_did_you_hear;
}
	public void setHow_did_you_hear(String how_did_you_hear) {
		this.how_did_you_hear = how_did_you_hear;
	}

	public String getUsage_model() {
		return this.usage_model;
}
	public void setUsage_model(String usage_model) {
		this.usage_model = usage_model;
	}

	public String getDescription() {
		return this.description;
}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
}
	public void setName(String name) {
		this.name = name;
	}

	public String getDeveloper_group_handle() {
		return this.developer_group_handle;
}
	public void setDeveloper_group_handle(String developer_group_handle) {
		this.developer_group_handle = developer_group_handle;
	}

	public String getNotes() {
		return this.notes;
}
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Boolean getAds() {
		return this.ads;
}
	public void setAds(Boolean ads) {
		this.ads = ads;
	}

	public Boolean getIs_packaged() {
		return this.is_packaged;
}
	public void setIs_packaged(Boolean is_packaged) {
		this.is_packaged = is_packaged;
	}

	public Boolean getCommercial() {
		return this.commercial;
}
	public void setCommercial(Boolean commercial) {
		this.commercial = commercial;
	}

	public List<Key> getKeys() {
		return keys;
	}

	public void setKeys(List<Key> keys) {
		this.keys = keys;
	}

	public List<Package_key> getPackage_keys() {
		return package_keys;
	}

	public void setPackage_keys(List<Package_key> package_keys) {
		this.package_keys = package_keys;
	}

	public List<Package> getPackages() {
		return packages;
	}

	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}

	public List<Plan> getPlans() {
		return plans;
	}

	public void setPlans(List<Plan> plans) {
		this.plans = plans;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getAudio_samples() {
		return audio_samples;
	}

	public void setAudio_samples(String audio_samples) {
		this.audio_samples = audio_samples;
	}

	public String getBrazil_tv_data_checkbox() {
		return brazil_tv_data_checkbox;
	}

	public void setBrazil_tv_data_checkbox(String brazil_tv_data_checkbox) {
		this.brazil_tv_data_checkbox = brazil_tv_data_checkbox;
	}

	public String getExperience_package_enable() {
		return experience_package_enable;
	}

	public void setExperience_package_enable(String experience_package_enable) {
		this.experience_package_enable = experience_package_enable;
	}

	public String getGeography_asia_checkbox() {
		return geography_asia_checkbox;
	}

	public void setGeography_asia_checkbox(String geography_asia_checkbox) {
		this.geography_asia_checkbox = geography_asia_checkbox;
	}

	public String getGeography_canada_checkbox() {
		return geography_canada_checkbox;
	}

	public void setGeography_canada_checkbox(String geography_canada_checkbox) {
		this.geography_canada_checkbox = geography_canada_checkbox;
	}

	public String getGeography_europe_checkbox() {
		return geography_europe_checkbox;
	}

	public void setGeography_europe_checkbox(String geography_europe_checkbox) {
		this.geography_europe_checkbox = geography_europe_checkbox;
	}

	public String getGeography_lamerica_checkbox() {
		return geography_lamerica_checkbox;
	}

	public void setGeography_lamerica_checkbox(String geography_lamerica_checkbox) {
		this.geography_lamerica_checkbox = geography_lamerica_checkbox;
	}

	public String getGeography_US_checkbox() {
		return geography_US_checkbox;
	}

	public void setGeography_US_checkbox(String geography_US_checkbox) {
		this.geography_US_checkbox = geography_US_checkbox;
	}

	public String getGeography_ww_checkbox() {
		return geography_ww_checkbox;
	}

	public void setGeography_ww_checkbox(String geography_ww_checkbox) {
		this.geography_ww_checkbox = geography_ww_checkbox;
	}

	public String getGetty_enable() {
		return getty_enable;
	}

	public void setGetty_enable(String getty_enable) {
		this.getty_enable = getty_enable;
	}

	public String getMovie_enabled() {
		return movie_enabled;
	}

	public void setMovie_enabled(String movie_enabled) {
		this.movie_enabled = movie_enabled;
	}

	public String getMusic_enabled() {
		return music_enabled;
	}

	public void setMusic_enabled(String music_enabled) {
		this.music_enabled = music_enabled;
	}

	public String getMusic_movie_metadata_checkbox() {
		return music_movie_metadata_checkbox;
	}

	public void setMusic_movie_metadata_checkbox(
			String music_movie_metadata_checkbox) {
		this.music_movie_metadata_checkbox = music_movie_metadata_checkbox;
	}

	public String getNet5_enabled() {
		return net5_enabled;
	}

	public void setNet5_enabled(String net5_enabled) {
		this.net5_enabled = net5_enabled;
	}

	public String getSbs6_enabled() {
		return sbs6_enabled;
	}

	public void setSbs6_enabled(String sbs6_enabled) {
		this.sbs6_enabled = sbs6_enabled;
	}

	public String getTV_listings_checkbox() {
		return TV_listings_checkbox;
	}

	public void setTV_listings_checkbox(String tV_listings_checkbox) {
		TV_listings_checkbox = tV_listings_checkbox;
	}

	public String getVariants_enabled() {
		return variants_enabled;
	}

	public void setVariants_enabled(String variants_enabled) {
		this.variants_enabled = variants_enabled;
	}

	public String getVariants_filter() {
		return variants_filter;
	}

	public void setVariants_filter(String variants_filter) {
		this.variants_filter = variants_filter;
	}

	public String getVeronica_enabled() {
		return veronica_enabled;
	}

	public void setVeronica_enabled(String veronica_enabled) {
		this.veronica_enabled = veronica_enabled;
	}

	public String getVideodetective() {
		return videodetective;
	}

	public void setVideodetective(String videodetective) {
		this.videodetective = videodetective;
	}

}
