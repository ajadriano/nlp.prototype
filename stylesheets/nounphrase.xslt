<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="NP[count(*)=2 and count(DT)=1 and count(JJ)=1]">
   <xsl:call-template name="noun_phrase_to_class">
        <xsl:with-param name="NP" select="." />
   </xsl:call-template>
</xsl:template>

<xsl:template match="NP[count(SBAR)=0 and count(NP)=0 and count(JJ)=0 and count(NN[@lemma!='everybody' and @lemma!='anyone']|NNS|NNP|NNPS)>0 and count(CC)=0]">
   <xsl:call-template name="noun_phrase_to_class">
	<xsl:with-param name="NP" select="." />
    </xsl:call-template>
</xsl:template>

<xsl:template match="WHNP[count(NN|NNS|NNP|NNPS)>0]">
   <xsl:call-template name="noun_phrase_to_class">
	<xsl:with-param name="NP" select="." />
    </xsl:call-template>
</xsl:template>

<xsl:template match="NP[NN/@lemma='everybody']">
   Thing()
</xsl:template>

<xsl:template match="NP[NN/@lemma='anyone']">
   Thing()
</xsl:template>

<xsl:template match="NN|NNS" priority="0">
   <xsl:call-template name="noun_to_class">
	<xsl:with-param name="noun" select="." />
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