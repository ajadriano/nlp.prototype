<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="VP[VBZ[@relcl]][NP]">
ObjectSomeValuesFrom(
    <xsl:call-template name="verb_to_object_property">
		<xsl:with-param name="verb" select="VBZ" />
	</xsl:call-template>
   	<xsl:text> </xsl:text>
   	<xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>)
</xsl:template>

</xsl:stylesheet>