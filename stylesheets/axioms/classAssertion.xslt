<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="/ROOT/S[NP[*[@entity]] and VP[*[@cop and @lemma='be']]]">
ClassAssertion(
    <xsl:apply-templates select="VP"/>
    <xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_individual">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="/ROOT/S[NNP[@entity] and VP[*[@cop and @lemma='be']]]">
ClassAssertion(
    <xsl:apply-templates select="VP"/>
    <xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_individual">
		<xsl:with-param name="noun" select="NNP" />
	</xsl:call-template>)
</xsl:template>

</xsl:stylesheet>