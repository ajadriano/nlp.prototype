<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>


<xsl:template match="/ROOT/SBARQ[SQ/VP/VBZ/@id=@root and WP and SQ[VP/NP[*[@entity]]]]">
GetInstances(ObjectHasValue( 
        <xsl:call-template name="verb_to_object_property_no_annotation">
		<xsl:with-param name="verb" select="SQ/VP/VBZ" />
	</xsl:call-template> 
        <xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_individual_no_annotation">
		<xsl:with-param name="NP" select="SQ/VP/NP" />
	</xsl:call-template>))
</xsl:template>

<xsl:template match="/ROOT/SBARQ[SQ/VP/VBZ/@id=@root and WP and SQ[VP/NNP[@entity]]]">
GetInstances(ObjectHasValue( 
        <xsl:call-template name="verb_to_object_property_no_annotation">
		<xsl:with-param name="verb" select="SQ/VP/VBZ" />
	</xsl:call-template> 
        <xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_individual_no_annotation">
		<xsl:with-param name="noun" select="SQ/VP/NNP" />
	</xsl:call-template>))
</xsl:template>

</xsl:stylesheet>