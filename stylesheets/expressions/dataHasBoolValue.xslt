<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="S[count(*)=1]/VP[(VB|VBZ)[@cop]][ADJP[*[@relcl]]]">
    <xsl:apply-templates select="ADJP"/>
</xsl:template>

</xsl:stylesheet>