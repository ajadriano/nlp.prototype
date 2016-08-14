<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template name="word_to_iri">
	<xsl:param name="text" />
	<xsl:value-of select="concat(upper-case(substring($text,1,1)), substring($text, 2))"/>
</xsl:template>

<xsl:template name="noun_phrase_to_iri">
   <xsl:param name="NP" />
   <xsl:for-each select="$NP/*[position()>1]/@lemma">
      <xsl:call-template name="word_to_iri">
		<xsl:with-param name="text" select="." />
	   </xsl:call-template>
   </xsl:for-each>
</xsl:template>

</xsl:stylesheet>