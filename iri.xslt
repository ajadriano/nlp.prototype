<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template name="word_to_iri">
	<xsl:param name="text" />
	<xsl:value-of select="concat(upper-case(substring($text,1,1)), substring($text, 2))"/>
</xsl:template>

<xsl:template name="verb_to_object_property">
	<xsl:param name="verb" />
	ObjectProperty(
	<xsl:call-template name="word_to_iri">
		<xsl:with-param name="text" select="$verb/@lemma" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:value-of select="$verb"/>)
</xsl:template>

<xsl:template name="noun_to_class">
	<xsl:param name="noun" />
	Class(
	<xsl:call-template name="word_to_iri">
		<xsl:with-param name="text" select="$noun/@lemma" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:value-of select="$noun"/>)
</xsl:template>

<xsl:template name="noun_phrase_to_class">
   <xsl:param name="NP" />
   Class(
   <xsl:for-each select="$NP/*[position()>1]/@lemma">
      <xsl:call-template name="word_to_iri">
		<xsl:with-param name="text" select="." />
	   </xsl:call-template>
   </xsl:for-each>
   <xsl:text> </xsl:text>
   <xsl:for-each select="$NP/*[position()>1]">
      <xsl:value-of select="."/>
	   <xsl:text> </xsl:text>
   </xsl:for-each>)
</xsl:template>

</xsl:stylesheet>