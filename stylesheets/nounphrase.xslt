<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="NP[count(SBAR)=0 and count(NP)=0 and count(CC)=0]">
   <xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="." />
	</xsl:call-template>
</xsl:template>
<xsl:template match="NNS" priority="0">
   <xsl:call-template name="noun_to_class">
	<xsl:with-param name="noun" select="." />
    </xsl:call-template>
</xsl:template>
<xsl:template match="NN[@entity]|NNP" priority="0">
   <xsl:call-template name="noun_to_individual">
	<xsl:with-param name="noun" select="." />
    </xsl:call-template>
</xsl:template>
<xsl:template match="NP[.//NNP|.//*[@entity]]" priority="0">
   <xsl:call-template name="noun_phrase_to_individual">
        <xsl:with-param name="NP" select="." />
   </xsl:call-template>
</xsl:template>
<xsl:template match="PRP" priority="0">
   <xsl:call-template name="pronoun_to_individual">
        <xsl:with-param name="prp" select="." />
    </xsl:call-template>
</xsl:template>

</xsl:stylesheet>