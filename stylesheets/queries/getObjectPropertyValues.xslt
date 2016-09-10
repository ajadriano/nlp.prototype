<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>


<xsl:template match="/ROOT/SBARQ[SQ/VP/(VB|VBZ)/@id=@root and WHNP and SQ[NP[NNP|NNPS]]]">
GetObjectPropertyValues(
	<xsl:call-template name="noun_phrase_to_individual_no_annotation">
		<xsl:with-param name="NP" select="SQ/NP" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="verb_to_object_property_no_annotation">
		<xsl:with-param name="verb" select="SQ/VP/(VB|VBZ)" />
	</xsl:call-template>)
</xsl:template>

</xsl:stylesheet>