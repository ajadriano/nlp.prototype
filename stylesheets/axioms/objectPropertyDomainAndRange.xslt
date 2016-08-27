<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="S[count(S)=0][VP/(VBP|VBZ)/@id=@root and NNS and VP[(VBP|VBZ)[@lemma!='have']] and VP[NNS]]">
ObjectPropertyDomain(
	<xsl:call-template name="verb_to_object_property">
		<xsl:with-param name="verb" select="VP/(VBP|VBZ)" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_class">
		<xsl:with-param name="noun" select="NNS" />
	</xsl:call-template>)
ObjectPropertyRange(
	<xsl:call-template name="verb_to_object_property">
		<xsl:with-param name="verb" select="VP/(VBP|VBZ)" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_class">
		<xsl:with-param name="noun" select="VP/NNS" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="S[count(S)=0][VP/(VBP|VBZ)/@id=@root and NP[.//DT[@lemma='a']] and VP[(VBP|VBZ)[@lemma!='have']] and VP[NNS]]">
ObjectPropertyDomain(
	<xsl:call-template name="verb_to_object_property">
		<xsl:with-param name="verb" select="VP/(VBP|VBZ)" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:apply-templates select="NP"/>)
ObjectPropertyRange(
	<xsl:call-template name="verb_to_object_property">
		<xsl:with-param name="verb" select="VP/(VBP|VBZ)" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_class">
		<xsl:with-param name="noun" select="VP/NNS" />
	</xsl:call-template>)
</xsl:template>

</xsl:stylesheet>