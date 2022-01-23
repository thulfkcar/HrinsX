package com.hrins.hrinsx.network.models

import com.google.gson.annotations.SerializedName


data class LinksDto (

  @SerializedName("mission_patch"       ) var missionPatch      : String?           = null,
  @SerializedName("mission_patch_small" ) var missionPatchSmall : String?           = null,
  @SerializedName("reddit_campaign"     ) var redditCampaign    : String?           = null,
  @SerializedName("reddit_launch"       ) var redditLaunch      : String?           = null,
  @SerializedName("reddit_recovery"     ) var redditRecovery    : String?           = null,
  @SerializedName("reddit_media"        ) var redditMedia       : String?           = null,
  @SerializedName("presskit"            ) var presskit          : String?           = null,
  @SerializedName("article_link"        ) var articleLink       : String?           = null,
  @SerializedName("wikipedia"           ) var wikipedia         : String?           = null,
  @SerializedName("video_link"          ) var videoLink         : String?           = null,
  @SerializedName("youtube_id"          ) var youtubeId         : String?           = null,
  @SerializedName("flickr_images"       ) var flickrImages      : ArrayList<String> = arrayListOf()

)