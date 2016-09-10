<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>


<xsl:template match="NP[*[@lemma='anyone'] and SBAR[WP|WDT][S]]">
    <xsl:apply-templates select="SBAR/S"/>
</xsl:template>

<xsl:template match="NP[*[@lemma='everybody'] and SBAR[WP|WDT][S]]">
    <xsl:apply-templates select="SBAR/S"/>
</xsl:template>

<xsl:template match="NP[count(SBAR)=0 and count(NP)=0 and count(JJ)=1 and count(NN|NNS|NNP|NNPS)=0 and count(CC)=0]">
   <xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="." />
	</xsl:call-template>
</xsl:template>
<xsl:template match="NP[count(SBAR)=0 and count(NP)=0 and count(JJ)=0 and count(NN|NNS|NNP|NNPS)>1 and count(CC)=0]">
   <xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="." />
	</xsl:call-template>
</xsl:template>
<xsl:template match="NN|NNS" priority="0">
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
<xsl:template match="ADJP[preceding-sibling::RB[@neg]]">
    DataHasBoolValue(
        <xsl:call-template name="adj_to_data_property">
            <xsl:with-param name="adj" select="JJ" />
        </xsl:call-template>
        <xsl:text> </xsl:text>
        false)
</xsl:template>
<xsl:template match="ADJP">
    DataHasBoolValue(
        <xsl:call-template name="adj_to_data_property">
            <xsl:with-param name="adj" select="JJ" />
        </xsl:call-template>
        <xsl:text> </xsl:text>
        true)
</xsl:template>

</xsl:stylesheet>