<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="VP[(VB|VBP|VBZ)[@cop and @lemma='be']][NP[preceding-sibling::RB[@neg]]]">
   ObjectComplementOf(<xsl:apply-templates select="NP"/>)
</xsl:template>
<xsl:template match="VP[preceding-sibling::RB[@neg]][(VB|VBP|VBZ)[@cop and @lemma='be']][NP]">
   ObjectComplementOf(<xsl:apply-templates select="NP"/>)
</xsl:template>

</xsl:stylesheet>