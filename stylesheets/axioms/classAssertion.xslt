<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="/ROOT/S[NP[*[@entity]] and VP[VBZ[@cop and @lemma='be'] and NP[count(PP)=0]]]">
ClassAssertion(
    <xsl:apply-templates select="VP/NP"/>
    <xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_individual">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="/ROOT/S[NNP[@entity] and VP[VBZ[@cop and @lemma='be'] and NP[count(PP)=0]]]">
ClassAssertion(
    <xsl:apply-templates select="VP/NP"/>
    <xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_individual">
		<xsl:with-param name="noun" select="NNP" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="/ROOT/S[VP/VBZ/@id=@root and NNP[@entity] and VP[NNS[count(@entity)=0]]]">
ClassAssertion(ObjectSomeValuesFrom(
        <xsl:call-template name="verb_to_object_property">
		<xsl:with-param name="verb" select="VP/VBZ" />
	</xsl:call-template> 
        <xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_class">
		<xsl:with-param name="noun" select="VP/NNS" />
	</xsl:call-template>)
    <xsl:text> </xsl:text>
    <xsl:call-template name="noun_to_individual">
        <xsl:with-param name="noun" select="NNP" />
    </xsl:call-template>)
</xsl:template>

<xsl:template match="/ROOT/S[VP/VBZ/@id=@root and NP[*[@entity]] and VP[NNS[count(@entity)=0]]]">
ClassAssertion(ObjectSomeValuesFrom(
        <xsl:call-template name="verb_to_object_property">
		<xsl:with-param name="verb" select="VP/VBZ" />
	</xsl:call-template> 
        <xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_class">
		<xsl:with-param name="noun" select="VP/NNS" />
	</xsl:call-template>)
    <xsl:text> </xsl:text>
    <xsl:call-template name="noun_phrase_to_individual">
        <xsl:with-param name="NP" select="NP" />
    </xsl:call-template>)
</xsl:template>

</xsl:stylesheet>