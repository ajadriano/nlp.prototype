<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>


<xsl:template match="/ROOT/SBARQ[SQ/VP/VBZ/@id=@root and WHNP[*[@nsubj]] and SQ[VP/NP[NNP|NNPS]]]">
GetInstances(ObjectHasValue( 
        <xsl:call-template name="verb_to_object_property_no_annotation">
		<xsl:with-param name="verb" select="SQ/VP/VBZ" />
	</xsl:call-template> 
        <xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_individual_no_annotation">
		<xsl:with-param name="NP" select="SQ/VP/NP" />
	</xsl:call-template>))
</xsl:template>


<xsl:template match="/ROOT/SBARQ[SQ/VP/VBZ/@id=@root and WHNP[*[@nsubj]] and SQ[VP[VBZ[@lemma='do']][S]]]">
GetInstances(ObjectIntersectionOf(
	<xsl:apply-templates select="WHNP/*[@nsubj]"/>
        <xsl:text> </xsl:text>
        <xsl:apply-templates select="SQ/VP/S"/>))
</xsl:template>

<xsl:template match="/ROOT/SBARQ[WHNP/*/@id=@root and SQ[(VBZ|VBP)[@cop] and NP]]">
GetInstances(<xsl:apply-templates select="SQ/NP"/>)
</xsl:template>

</xsl:stylesheet>