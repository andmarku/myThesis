Brief description
===
This is the exploratory search simulator that I used in my thesis. It is based around an Elasticsearch implementation which is used to search a database with academic articles.

About the thesis
===
In the thesis, I analyse the effect that adding two components to a search engine has on exploratory academic search using statistical methods (primarly a Linear Mixed Model). The main ambition with the thesis is to show how to apply the statistical analysis to a study of exploratory search, which is difficult because the topic lacks obvious measures of success retrieval. (Compare, for example, with the natural measures precision and recall used in regular look-up search.)

The components are added on top of my implementation of the open-source search engine Elasticsearch and will modify the search result using citation links between the documents and though combining different queries. The data used in the thesis is a roughly 200 GB data set consisting of scholarly documents (represented by their titles, references and citations). For those interested in using similar data, the data can very generously be downloaded from SemanticScholar (http://api.semanticscholar.org/corpus/download/).

About the repository
===
This repository contains the code I have written for testing my hypothesis. In more detail it has code for:
- generating random queries by sampling the database,
- retrieving search results from my local Elasticsearch server corresponding to queries,
- running simulations over the various combinations of components studied in the thesis
- saving results, settings, and queries to files.

It does not contain code for the analysis of the simulation results.
